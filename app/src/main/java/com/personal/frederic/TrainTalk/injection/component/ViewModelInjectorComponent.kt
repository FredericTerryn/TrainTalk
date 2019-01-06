package com.personal.frederic.TrainTalk.injection.component

import com.personal.frederic.TrainTalk.injection.NetworkModule
import com.personal.frederic.TrainTalk.viewModels.NmbsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ViewModelInjectorComponent {

        /**
         * Injects the dependencies into the specified NmbsViewmodel.
         */
        fun inject(nmbsViewModel: NmbsViewModel)

        @Component.Builder
        interface Builder {
            fun build(): ViewModelInjectorComponent

            fun networkModule(networkModule: NetworkModule): Builder
        }

    }
