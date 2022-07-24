package com.raywenderlich.ingredisearch

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class SearchTests {
    
    private lateinit var presenter : SearchPresenter
    private lateinit var view: SearchPresenter.View
    
    @Before
    fun setup() {
        presenter = SearchPresenter()
        view = mock()
        presenter.attachView(view)
    }
    
    @Test
    fun seach_withEmptyQuery_callsShowQueryRequiredMessage() {
        presenter.search("")
        verify(view).showQueryrequiredMessage()
    }
    
    @Test
    fun search_withEmptyQuery_doesNotCallsShowSearchResults() {
        presenter.search("")
        verify(view, never()).showSearchResults(anyString())
    }
}