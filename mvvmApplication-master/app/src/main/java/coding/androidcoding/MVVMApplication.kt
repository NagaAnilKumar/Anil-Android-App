package coding.androidcoding

import android.app.Application
import coding.androidcoding.data.db.AppDatabase
import coding.androidcoding.data.network.MyApi
import coding.androidcoding.data.network.NetworkConnectionInterceptor
import coding.androidcoding.data.preferences.PreferenceProvider
import coding.androidcoding.data.repositories.CardRepository
import coding.androidcoding.ui.MainViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { CardRepository(instance(), instance(), instance()) }
        bind() from provider { MainViewModelFactory(instance()) }
    }

}