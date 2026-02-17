import org.ktbridge.core.ClassConverter
import org.ktbridge.core.ClassLoader
import org.ktbridge.core.ClassProcessor
import org.ktbridge.core.transformer.TsTransformer
import org.ktbridge.sample.processor.AllCollections
import org.ktbridge.sample.processor.AllNonNullTypes
import org.ktbridge.sample.processor.AllNullableTypes
import org.ktbridge.sample.processor.SimpleEnum
import java.io.File
import java.net.URLClassLoader
import kotlin.test.Test
import kotlin.test.assertEquals

class ProcessorTests {

    private val converter = ClassConverter()
    private val root = File("build/classes/kotlin/main")
    private val loader = URLClassLoader(arrayOf(root.toURI().toURL()))


    @Test
    fun `should process correctly non nullable types`() {
        val clazz = ClassLoader(loader = loader).findTargetClass(AllNonNullTypes::class.java.name)
        val kClazz = ClassProcessor(converter, TsTransformer()).process(clazz)
        print(kClazz)
        assertEquals(
            kClazz, """export interface AllNonNullTypes {
	anyProp: any;
	bigDecimalProp: number;
	booleanProp: boolean;
	customProp: User;
	doubleProp: number;
	intProp: number;
	localDateProp: string;
	stringProp: string;
	uuidProp: string;
}
"""
        )

    }

    @Test
    fun `should process correctly  nullable types`() {
        val clazz = ClassLoader(loader = loader).findTargetClass(AllNullableTypes::class.java.name)
        val kClazz = ClassProcessor(converter, TsTransformer()).process(clazz)
        print(kClazz)
        assertEquals(
            kClazz, """export interface AllNullableTypes {
	anyProp?: any;
	bigDecimalProp?: number;
	booleanProp?: boolean;
	customProp?: User;
	doubleProp?: number;
	intProp?: number;
	listNullableProp: (string | null)[];
	listProp: string[];
	nullableLocalDateProp?: string;
	stringProp?: string;
	uuidProp?: string;
}
"""
        )

    }

    @Test
    fun `should process correctly collection types`() {
        val clazz = ClassLoader(loader = loader).findTargetClass(AllCollections::class.java.name)
        val kClazz = ClassProcessor(converter, TsTransformer()).process(clazz)
        print(kClazz)
        assertEquals(
            kClazz, """export interface AllCollections {
	listOfCustom: User[];
	listProp: string[];
	listWithNullableElementsProp: (string | null)[];
	mapProp: { [key: string]: number };
	nullableListProp: string[];
	setProp: number[];
}
"""
        )
    }

    @Test
    fun `should process correctly enum types`() {
        val clazz = ClassLoader(loader = loader).findTargetClass(SimpleEnum::class.java.name)
        val kClazz = ClassProcessor(converter, TsTransformer()).process(clazz)
        print(kClazz)
        assertEquals(
            kClazz, """export enum SimpleEnum {
	ONE = 'ONE',
	TWO = 'TWO',
	THREE = 'THREE',
}
"""
        )
    }


}