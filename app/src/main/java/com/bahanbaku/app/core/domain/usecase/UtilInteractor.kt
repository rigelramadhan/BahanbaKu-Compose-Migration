package com.bahanbaku.app.core.domain.usecase

import com.bahanbaku.app.core.data.Resource
import com.bahanbaku.app.core.data.remote.response.PaymentItem
import com.bahanbaku.app.core.domain.repository.IUtilRepository
import io.reactivex.Flowable

class UtilInteractor(private val utilRepository: IUtilRepository) : UtilUseCase {

    override fun getPaymentMethods(token: String): Flowable<Resource<List<PaymentItem>>> =
        utilRepository.getPaymentMethods(token)
}