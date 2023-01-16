package com.bahanbaku.app.core.domain.usecase

import com.bahanbaku.app.core.data.Resource
import com.bahanbaku.app.core.data.remote.response.PaymentItem
import io.reactivex.Flowable

interface UtilUseCase {
    fun getPaymentMethods(token: String): Flowable<Resource<List<PaymentItem>>>
}