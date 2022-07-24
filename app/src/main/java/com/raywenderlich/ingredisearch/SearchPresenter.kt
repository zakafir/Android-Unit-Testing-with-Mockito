class SearchPresenter {
    private var view : View? = null
    
    fun attachView(view: View) {
        this.view = view
    }
    
    fun detachView() {
        this.view = null
    }
    
    fun search(query: String) {
        if (query.trim().isBlank()) {
            view?.showQueryRequiredMessage()
        } else {
            view?.showSearchResults(query)
        }
    }
}

interface View {
    fun showQueryRequiredMessage()
    fun showSearchResults(query: String)
}