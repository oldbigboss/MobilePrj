package `fun`.veres.mobileprj.data.destination.cloud

import `fun`.veres.mobileprj.data.api.ApiController
import `fun`.veres.mobileprj.data.destination.cloud.mappers.DestinationJsonToBusinessMapper
import `fun`.veres.mobileprj.data.destination.cloud.model.DestinationJson
import `fun`.veres.mobileprj.domain.destination.DestinationBusiness
import `fun`.veres.mobileprj.utils.mapping.Mapper
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

interface DestinationCloudDataSource {
    suspend fun getDestinationsList(name: String) : List<DestinationBusiness>
}

class DestinationCloudDataSourceImpl(
    private val apiController: ApiController,
    private val mapper: Mapper<DestinationJson, List<DestinationBusiness>> = DestinationJsonToBusinessMapper()
) : DestinationCloudDataSource {

    override suspend fun getDestinationsList(name: String) : List<DestinationBusiness> {
        return withContext(IO) {
            mapper.map(
                apiController.destinationService.getDestination(name)
            )
        }
    }

}
