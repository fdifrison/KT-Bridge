import org.ktbridge.core.ClassLoader
import org.ktbridge.sample.scanner.content.CreateContentDTO
import org.ktbridge.sample.scanner.territory.CreateTerritoryDTO
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ScannerTests {

    @Test
    fun `should find all the annotated class in the build folder`() {
        val foundClasses = ClassLoader().findTargetClasses()
        println("Found: ${foundClasses.map { it.simpleName }}")
        assertTrue(foundClasses.contains(CreateContentDTO::class.java))
        assertTrue(foundClasses.contains(CreateTerritoryDTO::class.java))
    }

    @Test
    fun `should find only annotated class in the specific build folder`() {
        val foundClasses = ClassLoader().findTargetClasses(subPackageName = "content")
        println("Found: ${foundClasses.map { it.simpleName }}")
        assertTrue(foundClasses.contains(CreateContentDTO::class.java))
        assertFalse(foundClasses.contains(CreateTerritoryDTO::class.java))
    }


}