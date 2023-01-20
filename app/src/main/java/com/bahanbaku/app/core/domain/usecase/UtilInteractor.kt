package com.bahanbaku.app.core.domain.usecase

import com.bahanbaku.app.core.data.Resource
import com.bahanbaku.app.core.data.remote.response.PaymentItem
import com.bahanbaku.app.core.domain.repository.IUtilRepository
import kotlinx.coroutines.flow.Flow

class UtilInteractor(private val utilRepository: IUtilRepository) : UtilUseCase {

    override fun getPaymentMethods(token: String): Flow<Resource<List<PaymentItem>>> =
        utilRepository.getPaymentMethods(token)
}