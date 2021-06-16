package `fun`.veres.mobileprj.domain.destination

import `fun`.veres.mobileprj.data.destination.DestinationRepository
import javax.inject.Inject

class GetDestinationUseCase @Inject constructor(
    private val destinationRepository: DestinationRepository
) {
    suspend operator fun invoke(name: String): List<DestinationBusiness> =
        destinationRepository.getDestinationsList(name)
}