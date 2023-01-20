package com.bahanbaku.app.core.domain.usecase

import com.bahanbaku.app.core.data.Resource
import com.bahanbaku.app.core.data.remote.response.PaymentItem
import kotlinx.coroutines.flow.Flow

interface UtilUseCase {
    fun getPaymentMethods(token: String): Flow<Resource<List<PaymentItem>>>
}