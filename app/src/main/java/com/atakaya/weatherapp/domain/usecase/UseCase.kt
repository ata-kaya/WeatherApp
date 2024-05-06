package com.atakaya.weatherapp.domain.usecase

abstract class UseCase<T> {
    abstract fun execute(): T
}

abstract class ParameterizedUseCase<T, P> {
    abstract fun exec(params: P): T
}
