package com.bahanbaku.app.core.domain.repository

import com.bahanbaku.app.core.data.Resource
import com.bahanbaku.app.core.data.remote.response.PaymentItem
import io.reactivex.Flowable

interface IUtilRepository {
    fun getPaymentMethods(token: String): Flowable<Resource<List<PaymentItem>>>
}