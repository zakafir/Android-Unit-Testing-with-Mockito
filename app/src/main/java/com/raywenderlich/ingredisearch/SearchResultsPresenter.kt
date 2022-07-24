class SearchResultsPresenter(val repository = RecipeRepository): BasePresenter<SearchResultsPresenter.View>() {
    private var view: SearchResultsPresenter.View? = null
    private var recipes = List<Recipe>? = null
    
    fun search(query: String) {
        view?.showLoading()
        repository.getRecipes(query, object : RepositoryCallback<List<Recipe>> {
            
            override fun onSuccess(recipes: List<Recipe>?) {
                this@SearchResultsPresenter.recipes = recipes
                if (recipes != null && recipes.isNotEmpty()) {
                    view?.showRecipes(recipes)
                } else {
                    view?.showEmptyRecipes()
                }
            }
            
            override fun onError() {
                view?.showError()
            }
        })
    }
    
    fun addFavorite(recipe: Recipe) {
        recipe.isFavorited = true
        repository.addFavorite(recipe)
        val recipeIndex = recipe?.indexOf(recipe)
        if (recipeIndex != null) {
            view?.refreshFavoriteStatus(recipeIndex)
        }
    }
    
    fun removeFavorite(recipe: Recipe) {
        repository.removeFavorite(recipe)
        recipe.isFavorited = false
        val recipeIndex = recipe?.indexOf(recipe)
        if (recipeIndex != null) {
            view?.refreshFavoriteStatus(recipeIndex)
        }
    }
    
}

interface View {
    fun showLoading()
    fun showRecipes(recipes: List<Recipe>)
    fun showEmptyRecipes()
    fun showError()
    fun refreshFavoriteStatus(recipeIndex: Int)
}