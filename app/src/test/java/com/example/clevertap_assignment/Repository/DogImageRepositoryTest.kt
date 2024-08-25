package com.example.clevertap_assignment.Repository

import com.example.clevertap_assignment.Data.DAO.TableDAO
import com.example.clevertap_assignment.Data.Entity.DogTable
import com.example.dogimagelibrary.DogImageLibrary
import com.example.dogimagelibrary.IOModel.ImageModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class DogImageRepositoryTest {


    @Mock
    private lateinit var tableDAO: TableDAO

    @Mock
    private lateinit var dogImageLibrary: DogImageLibrary

    @Mock
    private lateinit var repository: DogImageRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = DogImageRepository(tableDAO, dogImageLibrary)
    }

    @Test
    fun getImage() =runBlocking{

        val imageModel = ImageModel("testUrl")
        val id = 1L
        val dogTable = DogTable(id.toInt(), "testUrl")

        `when`(dogImageLibrary.getImage()).thenReturn(imageModel)
        `when`(tableDAO.insert(any(DogTable::class.java) ?: dogTable)).thenReturn(id)
        `when`(tableDAO.getImageModelFromID(id.toInt())).thenReturn(dogTable)

        val result = repository.getImage()

        assertEquals(dogTable, result)

    }

    @Test
    fun getNextImage_when_nextImage_exist() =runBlocking{
        val currentId = 1
        val nextDogTable = DogTable(2, "nextUrl")

        repository.currID = currentId
        `when`(tableDAO.getNextImage(currentId)).thenReturn(nextDogTable)

        val result = repository.getNextImage()
        assertEquals(nextDogTable, result)
    }

    @Test
    fun `getNextImage_when_nextimage_not_exist`() =runBlocking{
        val currentId = 1
        val nImageModel = ImageModel("newTestUrl")
        val id = 2L
        val nDogTable = DogTable( id.toInt(), "newTestUrl")

        repository.currID = currentId
        `when`(tableDAO.getNextImage(currentId)).thenReturn(null)
        `when`(dogImageLibrary.getImage()).thenReturn(nImageModel)
        `when`(tableDAO.insert(any(DogTable::class.java) ?: nDogTable)).thenReturn(id)
        `when`(tableDAO.getImageModelFromID(id.toInt())).thenReturn(nDogTable)

        val result = repository.getNextImage()
        assertEquals(nDogTable, result)
    }


    @Test
    fun getPreviousImage_when_exist() =runBlocking{
        val currentId = 2
        val prevDogTable = DogTable(id = 1, imageUrl = "prevUrl")

        repository.currID = currentId
        `when`(tableDAO.getPrviousImage(currentId)).thenReturn(prevDogTable)

        val result = repository.getPreviousImage()

        assertEquals(prevDogTable, result)
    }

    @Test
    fun getPrevious_when_null() =runBlocking{
        var currentId = 1
        repository.currID = currentId
        `when`(tableDAO.getPrviousImage(currentId)).thenReturn(null)
        var result = repository.getPreviousImage()
        assertEquals(result, null)
    }


    @Test
    fun `test getImageLists`() =runBlocking{
        val imageModels =listOf(ImageModel("url1"), ImageModel("url2"))
        val n = 2

        `when`(dogImageLibrary.getImages(n)).thenReturn(imageModels)

        val result = repository.getImageLists(n)
        assertEquals(imageModels, result)
    }

}