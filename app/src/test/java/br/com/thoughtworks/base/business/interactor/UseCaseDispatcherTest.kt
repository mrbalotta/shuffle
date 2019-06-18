package br.com.thoughtworks.base.business.interactor

import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.lang.Exception
import java.lang.RuntimeException
import java.util.concurrent.ExecutorService

open class UseCaseDispatcherTest {

    protected val useCase: UseCase<Nothing,String> = mock()
    private lateinit var executeOn: ExecutorService
    private lateinit var resultOn: ExecutorService
    private lateinit var dispatcher: UseCaseDispatcher<Nothing, Output<String>>

    @Before
    fun setup() {
        setupExecutorExecuteOn()
        setupExecutorResultOn()
        setupInvoker()
    }

    class GuardDenialTest: UseCaseDispatcherTest() {
        @Test
        fun whenDeniedByGuard_dispatchExecuteOnOnly() {
            arrangeGuard()
            runInvoker()
            assertOnExecuteExecutorOnly()
        }

        private fun arrangeGuard() {
            whenever(useCase.guard()).thenReturn(false)
        }

        @Test
        fun whenDeniedByGuard_assertCallbacks() {
            arrangeGuard()
            runInvoker()

            assertInvokerCallbacks("BD", "BE", "!BS", "!BR")
            verify(useCase, times(1)).onGuardError()
        }
    }

    class GuardAllowanceTest: UseCaseDispatcherTest() {
        @Test
        fun whenAllowedByGuard_dispatchBoth() {
            arrangeGuard()
            runInvoker()
            assertBoth()
        }

        @Test
        fun whenAllowedByGuard_assertCallbacks() {
            arrangeGuard()

            runInvoker()

            assertInvokerCallbacks("BD", "BE", "BS", "!BR")
            verify(useCase, times(0)).onGuardError()
        }

        private fun arrangeGuard() {
            whenever(useCase.guard()).thenReturn(true)
        }
    }

    class GuardThrowsErrorTest: UseCaseDispatcherTest() {
        @Test
        fun whenGuardThrowsError_dispatchBoth() {
            arrangeException()
            runInvoker()
            assertBoth()
        }

        @Test
        fun whenGuardThrowsError_assertCallbacks() {
            val exception = arrangeException()

            runInvoker()

            assertInvokerCallbacks("BD", "BE", "!BS", "BR")
            verify(useCase, times(0)).onGuardError()
            verify(useCase, times(1)).onError(eq(exception))
        }

        private fun arrangeException(): Exception {
            val exception = RuntimeException()
            whenever(useCase.guard()).thenThrow(exception)
            return exception
        }
    }

    class ExecuteThrowsErrorTest: UseCaseDispatcherTest() {

        @Test
        fun whenExecuteThrowsError_assertCallbacks() {
            val exception = arrangeException()

            runInvoker()

            assertInvokerCallbacks("BD", "BE", "!BS", "BR")
            verify(useCase, times(0)).onGuardError()
            verify(useCase, times(1)).onError(eq(exception))
        }

        @Test
        fun whenExecuteThrowsError_dispatchBoth() {
            arrangeException()
            runInvoker()
            assertBoth()
        }

        private fun arrangeException(): Exception {
            val exception = RuntimeException()
            whenever(useCase.guard()).thenReturn(true)
            whenever(useCase.execute()).thenThrow(exception)
            return exception
        }
    }


    protected fun runInvoker() {
        runBlocking {
            dispatcher.dispatch {}
        }
    }

    protected fun assertInvokerCallbacks(vararg args: String) {
        val map = mapOf(
            "BD"  to { verify(dispatcher, times(1)).onBeforeDispatch() },
            "!BD" to { verify(dispatcher, times(0)).onBeforeDispatch() },
            "BE"  to { verify(dispatcher, times(1)).onBeforeExecute() },
            "!BE" to { verify(dispatcher, times(0)).onBeforeExecute() },
            "BS"  to { verify(dispatcher, times(1)).onBeforeSuccess() },
            "!BS" to { verify(dispatcher, times(0)).onBeforeSuccess() },
            "BR"  to { verify(dispatcher, times(1)).onBeforeError() },
            "!BR" to { verify(dispatcher, times(0)).onBeforeError() }
        )

        for(arg in args) {
            map[arg]?.invoke()
        }
    }

    protected fun assertOnExecuteExecutorOnly() {
        verify(executeOn, times(1)).execute(any())
        verify(resultOn, times(0)).execute(any())
    }

    protected fun assertBoth() {
        verify(executeOn, times(1)).execute(any())
        verify(resultOn, times(1)).execute(any())
    }

    private fun setupExecutorResultOn() {
        resultOn = mock()
        setupExecutorAnswer(resultOn)
    }

    private fun setupExecutorExecuteOn() {
        executeOn = mock()
        setupExecutorAnswer(executeOn)
    }

    private fun setupInvoker() {
        val invoker = UseCaseDispatcher(
            useCase = useCase,
            executeOn = executeOn.asCoroutineDispatcher(),
            resultOn = resultOn.asCoroutineDispatcher()
        )

        this.dispatcher = spy(invoker)
    }

    private fun setupExecutorAnswer(service: ExecutorService) {
        doAnswer {
            val runnable = it.arguments[0] as Runnable
            runnable.run()
            null
        }.`when`(service).execute(any())
    }
}