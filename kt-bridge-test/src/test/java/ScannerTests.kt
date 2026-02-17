import org.ktbridge.core.ClassLoader
import org.ktbridge.sample.scanner.content.CreateContentDTO
import org.ktbridge.sample.scanner.territory.CreateTerritoryDTO
import java.io.File
import java.net.URLClassLoader
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ScannerTests {

    private val root = File("build/classes/kotlin/main")
    private val loader = URLClassLoader(arrayOf(root.toURI().toURL()))

    @Test
    fun `should find all the annotated class in the build folder`() {
        val foundClasses = ClassLoader(loader = loader).findTargetClasses()
        assertTrue(foundClasses.contains(CreateContentDTO::class.java))
        assertTrue(foundClasses.contains(CreateTerritoryDTO::class.java))
    }

    @Test
    fun `should find only annotated class in the specific build folder`() {
        val foundClasses = ClassLoader(loader = loader).findTargetClasses(subPackageName = listOf("content"))
        assertTrue(foundClasses.contains(CreateContentDTO::class.java))
        assertFalse(foundClasses.contains(CreateTerritoryDTO::class.java))
    }


}