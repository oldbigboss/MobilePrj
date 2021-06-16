package `fun`.veres.mobileprj.data.destination.cloud.model

import com.google.gson.annotations.SerializedName

data class DestinationJson(
    @SerializedName("cities") val cities: List<CityJson>?,
    @SerializedName("hotels") val hotels: List<HotelJson>?
)

data class CityJson(
    @SerializedName("countryCode") val countryCode: String?,
    @SerializedName("country") val country: String?,
    @SerializedName("latinFullName") val latinFullName: String?,
    @SerializedName("fullname") val fullName: String?,
    @SerializedName("clar") val countyName: String?,
    @SerializedName("latinClar") val latinCountryName: String?,
    @SerializedName("location") val location: LocationJson?,
    @SerializedName("hotelsCount") val hotelsCount: Int?,
    @SerializedName("iata") val iata: List<String>?,
    @SerializedName("city") val city: String?,
    @SerializedName("latinCity") val latinCity: String?,
    @SerializedName("timezone") val timezone: String?,
    @SerializedName("timezonesec") val timezonesec: Int?,
    @SerializedName("latinCountry") val latinCountry: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("countryId") val countryId: Int?,
    @SerializedName("_score") val score: Long?,
    @SerializedName("isOutOfService") val isOutOfService: Boolean?,
    @SerializedName("state") val state: Any?
)

data class LocationJson(
    @SerializedName("lat") val lat: Double?,
    @SerializedName("lon") val lon: Double?
)

data class HotelJson(
    @SerializedName("state") val state: Any?,
    @SerializedName("stars") val stars: Int?,
    @SerializedName("locationFullName") val locationFullName: String?,
    @SerializedName("latinLocationFullName") val latinLocationFullName: String?,
    @SerializedName("hotelFullName") val hotelFullName: String?,
    @SerializedName("location") val location: LocationJson?,
    @SerializedName("timezone") val timezone: String?,
    @SerializedName("timezoneSec") val timezonesec: Int?,
    @SerializedName("id") val id: Int?,
    @SerializedName("locationId") val locationId: Int?,
    @SerializedName("photoCount") val photoCount: Int?,
    @SerializedName("city") val city: String?,
    @SerializedName("latinCity") val latinCity: String?,
    @SerializedName("clar") val countyName: String?,
    @SerializedName("latinClar") val latinCountryName: String?,
    @SerializedName("latinCountry") val latinCountry: String?,
    @SerializedName("locationHotelsCount") val locationHotelsCount: Int?,
    @SerializedName("rating") val rating: Int?,
    @SerializedName("country") val country: String?,
    @SerializedName("distance") val distance: Float?,
    @SerializedName("_score") val _score: Double?,
    @SerializedName("name") val name: String?,
    @SerializedName("latinName") val latinName: String?,
    @SerializedName("address") val address: String?,
    @SerializedName("photos") val photos: List<Long>?,
    @SerializedName("countryId") val countryId: Int?,
    @SerializedName("isOutOfService") val isOutOfService: Boolean?
)