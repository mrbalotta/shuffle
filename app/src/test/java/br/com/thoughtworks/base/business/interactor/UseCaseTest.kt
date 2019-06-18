package br.com.thoughtworks.base.business.interactor

import com.nhaarman.mockitokotlin2.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class UseCaseTest {
    private val param = "param"
    private val result = "result"
    private val output: Output<String?> = ValueOutput(result)
    private lateinit var useCase: MockUseCase
    private lateinit var callback: Callback<String?>

    @Before
    fun setup() {
        callback = spy(Callback())
        useCase = spy(MockUseCase(output))
    }

    @Test
    fun whenDeniedByGuard_thenCallOnGuardError() {
        whenever(useCase.guard(eq(param))).thenReturn(false)

        useCase.process(param){callback.run(it)}

        verify(useCase, times(0)).execute(any())
        verify(useCase, times(0)).onSuccess(any())
        verify(useCase, times(1)).onGuardError()
        verify(callback, times(0)).run(any())
    }

    @Test
    fun whenAllowedByGuard_thenCallOnSuccessInvokeCallback() {
        useCase.process(param){callback.run(it)}

        verify(useCase, times(1)).execute(eq(param))
        verify(useCase, times(1)).onSuccess(eq(output))
        verify(callback, times(1)).run(eq(output))
        assertEquals(callback.result.value, result)
    }

    @Test
    fun whenGuardThrowsError_thenCallOnErrorInvokeCallback() {
        val exception = RuntimeException()
        whenever(useCase.guard(eq(param))).thenThrow(exception)

        useCase.process(param){callback.run(it)}

        verify(useCase, times(0)).execute(eq(param))
        verify(useCase, times(0)).onSuccess(any())
        verify(useCase, times(1)).onError(eq(exception))
        verify(callback, times(1)).run(any())
        assertEquals(callback.result.error, exception)
    }

    @Test
    fun whenExecuteThrowsError_thenCallOnErrorInvokeCallback() {
        val exception = RuntimeException()
        whenever(useCase.execute(eq(param))).thenThrow(exception)

        useCase.process(param){callback.run(it)}

        verify(useCase, times(1)).execute(eq(param))
        verify(useCase, times(0)).onSuccess(any())
        verify(useCase, times(1)).onError(eq(exception))
        verify(callback, times(1)).run(any())
        assertEquals(callback.result.error, exception)
    }
}

class Callback<R> {
    lateinit var result: Output<R>

    fun run(value: Output<R>) {
        this.result = value
    }
}

class MockUseCase(private val result: Output<String?>): UseCase<String?, String?>() {
    override fun execute(param: String?): Output<String?> {
        return result
    }
}