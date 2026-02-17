# KT-Bridge

**Version:** 0.0.1

A Gradle plugin library for generating TypeScript definitions from Kotlin data classes, enabling seamless type-safe contract sharing between backend (Kotlin) and frontend (TypeScript) applications.

## Overview

KT-Bridge automatically converts Kotlin DTOs annotated with `@GenerateTypeScript` into TypeScript interfaces, maintaining type safety across your full-stack application. The library uses Kotlin reflection to analyze class structures and generates corresponding TypeScript definitions with proper type mappings, nullability handling, and collection support.

## Features

- **Automatic Type Conversion**: Maps Kotlin types to TypeScript equivalents
- **Nullability Support**: Preserves Kotlin's nullable types as optional TypeScript properties
- **Collection Handling**: Supports `List`, `Set`, and `Map` with proper TypeScript representations
- **Package Structure Preservation**: Maintains your package hierarchy in the generated output
- **Gradle Integration**: Simple Gradle plugin for seamless build integration
- **Annotation-Based**: Mark classes for generation with `@GenerateTypeScript`

## Project Structure

```
KT-Bridge/
├── kt-bridge-annotations/    # Annotation definitions
├── kt-bridge-core/           # Core conversion logic
├── kt-bridge-gradle-plugin/  # Gradle plugin implementation
└── kt-bridge-test/           # Test samples
```

## Installation

### 1. Configure Your Project

#### `settings.gradle.kts`

Include KT-Bridge as a composite build and configure dependency substitution:

```kotlin
rootProject.name = "your-project-name"

pluginManagement {
    includeBuild("../KT-Bridge")
}

includeBuild("../KT-Bridge") {
    dependencySubstitution {
        substitute(module("org.ktbridge:kt-bridge-annotations"))
            .using(project(":kt-bridge-annotations"))
    }
}
```

#### `build.gradle.kts`

Apply the plugin and add the annotations dependency:

```kotlin
plugins {
    kotlin("jvm") version "2.3.0"
    id("org.ktbridge.generator")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.ktbridge:kt-bridge-annotations:0.0.1")
}
```

### 2. Configure the Generation Task

Add task configuration to specify output directory and packages to scan:

```kotlin
import org.ktbridge.plugin.KBridgeTask

tasks.withType<KBridgeTask> {
    classpath.from(project.configurations.getByName("runtimeClasspath"))
    outputDir.convention(project.layout.projectDirectory.dir("generated-ts"))
    packages = listOf("content", "territory", "user") // Package names to scan
}
```

## Usage

### 1. Annotate Your Kotlin Classes

Mark your DTOs with `@GenerateTypeScript`:

```kotlin
package com.example.content

import org.ktbridge.annotations.GenerateTypeScript

@GenerateTypeScript
data class CreateContentDTO(
    val parentId: String?,
    val type: ContentType,
    val internalTitle: String,
    val taxonomies: List<String>?
)

@GenerateTypeScript
enum class ContentType {
    SIMPLE,
    EVENT
}
```

### 2. Generate TypeScript Definitions

Run the Gradle task:

```bash
./gradlew generateTypeScript
```

### 3. Generated Output

The plugin generates TypeScript interfaces in the specified output directory, preserving package structure:

**`generated-ts/com/example/content/CreateContentDTO.ts`**
```typescript
export interface CreateContentDTO {
    parentId?: string;
    type: ContentType;
    internalTitle: string;
    taxonomies?: string[];
}
```

**`generated-ts/com/example/content/ContentType.ts`**
```typescript
export interface ContentType {
}
```

## Type Mappings

KT-Bridge automatically maps Kotlin types to TypeScript:

| Kotlin Type | TypeScript Type |
|-------------|----------------|
| `String` | `string` |
| `Int`, `Long`, `Short`, `Byte` | `number` |
| `Float`, `Double` | `number` |
| `Boolean` | `boolean` |
| `List<T>` | `T[]` |
| `Set<T>` | `T[]` |
| `Map<K, V>` | `{ [key: K]: V }` |
| Custom Classes | Interface name |
| Nullable types (`T?`) | Optional property (`prop?: T`) |

## Advanced Configuration

### Custom Output Directory

```kotlin
tasks.withType<KBridgeTask> {
    outputDir.convention(project.layout.projectDirectory.dir("frontend/src/types"))
}
```

### Multiple Package Scanning

```kotlin
tasks.withType<KBridgeTask> {
    packages = listOf(
        "com.example.dto.user",
        "com.example.dto.content",
        "com.example.dto.territory"
    )
}
```

### Integration with Build Process

Automatically generate types before compilation:

```kotlin
tasks.named("compileKotlin") {
    dependsOn("generateTypeScript")
}
```

## Examples

### Complex DTO with Collections

**Kotlin:**
```kotlin
@GenerateTypeScript
data class UserDTO(
    val id: String,
    val name: String,
    val email: String?,
    val roles: List<String>,
    val metadata: Map<String, String>?,
    val tags: Set<String>
)
```

**Generated TypeScript:**
```typescript
export interface UserDTO {
    id: string;
    name: string;
    email?: string;
    roles: string[];
    metadata?: { [key: string]: string };
    tags: string[];
}
```

### Nested DTOs

**Kotlin:**
```kotlin
@GenerateTypeScript
data class AddressDTO(
    val street: String,
    val city: String,
    val zipCode: String?
)

@GenerateTypeScript
data class PersonDTO(
    val name: String,
    val address: AddressDTO,
    val alternateAddresses: List<AddressDTO>?
)
```

**Generated TypeScript:**
```typescript
export interface AddressDTO {
    street: string;
    city: string;
    zipCode?: string;
}

export interface PersonDTO {
    name: string;
    address: AddressDTO;
    alternateAddresses?: AddressDTO[];
}
```

## Build Requirements

- **Kotlin:** 2.3.0+
- **Gradle:** 9.2.0+
- **JVM:** 25+

## Module Dependencies

The library consists of three main modules:

- **kt-bridge-annotations**: Runtime annotations (`@GenerateTypeScript`)
- **kt-bridge-core**: Core conversion engine with reflection-based type mapping
- **kt-bridge-gradle-plugin**: Gradle plugin (`org.ktbridge.generator`)

## Limitations

- Only classes annotated with `@GenerateTypeScript` are processed
- Enum classes generate empty interfaces (values are not preserved)
- Generic type parameters are resolved at usage site
- Requires compiled classes (runs after Kotlin compilation)

## Development

### Building the Library

```bash
./gradlew build
```

### Running Tests

```bash
./gradlew test
```

### Publishing Locally

```bash
./gradlew publishToMavenLocal
```

## License

This project is part of the KT-Bridge ecosystem for bridging Kotlin contracts to TypeScript.

## Contributing

Contributions are welcome! Please ensure all tests pass before submitting pull requests.

---

**KT-Bridge** - Bridging contracts to TypeScript, one DTO at a time.
