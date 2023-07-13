package com.example.lemonade

enum class Stage(
    val imageResId: Int,
    val imageDescriptionResId: Int,
    val instructionResId: Int,
    var nextStage: () -> Stage,
) {
    TREE(R.drawable.lemon_tree,
        R.string.lemon_tree,
        R.string.lemon_tree_instructions,
        { LEMON }),
    LEMON(R.drawable.lemon_squeeze,
        R.string.lemon_squeeze,
        R.string.lemon_squeeze_instructions,
        { LEMONADE }),
    LEMONADE(R.drawable.lemon_drink,
        R.string.lemon_drink,
        R.string.glass_of_lemonade_instructions,
        { EMPTY_GLASS }),
    EMPTY_GLASS(R.drawable.lemon_restart,
        R.string.lemon_restart,
        R.string.lemon_restart_instructions,
        { TREE });
}