package br.com.psi.tknotes.tknotesapi

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TknotesApiApplicationTests {

	@Test
	fun contextLoads() {
	}

}
/*
@RunWith(SpringRunner::class)
@ContextConfiguration(classes = arrayOf(CityConfig::class))
@DataJpaTest
internal class JpaCityServiceTest {

    @Autowired
    lateinit var service: CityService
    @Autowired
    lateinit var repository: CityRepository

    @get:Rule
    var softly = JUnitSoftAssertions()

    @Test
    fun `'retrieveCities' should retrieve empty list if repository doesn't contain entities`() {
        assertThat(service.retrieveCities()).isEmpty()
    }

    @Test
    fun `'retrieveCity' should return null if city for cityId doesnt exist`() {
        assertThat(service.retrieveCity(-99)).isNull()
    }

    @Test
    fun `'retrieveCity' should map existing entity from repository`() {
        repository.save(CityEntity("cityname", "description"))

        val result = service.retrieveCity("city")
        softly.assertThat(result).isNotNull
        softly.assertThat(result?.id).isNotNull
        softly.assertThat(result?.name).isEqualTo("cityname")
        softly.assertThat(result?.description).isEqualTo("description")
    }

    @Test
    fun `'addCity' should return created entity`() {
        val result = service.addCity(CreateCityDto("name", "description"))
        softly.assertThat(result.id).isNotNull()
        softly.assertThat(result.name).isEqualTo("name")
        softly.assertThat(result.description).isEqualTo("description")
    }

    @Test
    fun `'updateCity' should update existing values`() {
        val existingCity = repository.save(CityEntity("cityname", "description")).toDto()

        val result = service.updateCity(existingCity.id, UpdateCityDto("new name", "new description"))
        softly.assertThat(result).isNotNull
        softly.assertThat(result?.id).isEqualTo(existingCity.id)
        softly.assertThat(result?.name).isEqualTo("new name")
        softly.assertThat(result?.description).isEqualTo("new description")
    }

    @Test
    fun `'updateCity' shouldn't update null values`() {
        val existingCity = repository.save(CityEntity("cityname", "description")).toDto()

        val result = service.updateCity(existingCity.id, UpdateCityDto(null, null))
        softly.assertThat(result).isNotNull
        softly.assertThat(result?.id).isEqualTo(existingCity.id)
        softly.assertThat(result?.name).isEqualTo("cityname")
        softly.assertThat(result?.description).isEqualTo("description")
    }
 
 */