package `fun`.veres.mobileprj.di.qualifiers

import javax.inject.Qualifier

/**
 * На случай, если будет несколько клиентов
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RestClient