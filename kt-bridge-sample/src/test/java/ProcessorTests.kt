import org.ktbridge.core.ClassLoader
import org.ktbridge.core.ClassProcessor
import org.ktbridge.sample.processor.AllNonNullTypes
import kotlin.test.Test

class ProcessorTests {

    @Test
    fun `should process correctly non nullable types`() {
        val clazz = ClassLoader().findTargetClass(AllNonNullTypes::class.java.name)
        val kClazz = ClassProcessor().process(clazz)
        println(kClazz)

    }


}