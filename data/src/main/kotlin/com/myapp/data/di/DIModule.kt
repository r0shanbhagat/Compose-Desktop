package com.myapp.data.di

import com.myapp.data.domain.repository.Repository
import com.myapp.data.remote.RemoteDataService
import com.myapp.data.remote.RemoteDataSourceImpl
import com.myapp.data.repo.RepositoryImpl
import org.koin.dsl.module


val dataModule = module {
    single<Repository> { RepositoryImpl(get()) }
    single<RemoteDataService> { RemoteDataSourceImpl() }
}
