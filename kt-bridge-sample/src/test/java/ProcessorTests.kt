import org.ktbridge.core.ClassConverter
import org.ktbridge.core.ClassLoader
import org.ktbridge.core.ClassProcessor
import org.ktbridge.sample.processor.AllCollections
import org.ktbridge.sample.processor.AllNonNullTypes
import kotlin.test.Test

class ProcessorTests {

    private val converter = ClassConverter()

    @Test
    fun `should process correctly non nullable types`() {
        val clazz = ClassLoader().findTargetClass(AllNonNullTypes::class.java.name)
        val kClazz = ClassProcessor(converter).process(clazz)
        println(kClazz.prettyPrint())

    }

    @Test
    fun `should process correctly collection types`() {
        val clazz = ClassLoader().findTargetClass(AllCollections::class.java.name)
        val kClazz = ClassProcessor(converter).process(clazz)
        println(kClazz.prettyPrint())

    }


}