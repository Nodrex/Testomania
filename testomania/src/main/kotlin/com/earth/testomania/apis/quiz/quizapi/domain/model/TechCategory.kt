package com.earth.testomania.apis.quiz.quizapi.domain.model

import com.earth.testomania.R

/**
 * This is used to choose illustration for specific tech type
 */
enum class TechCategory(val category: String, val illustration: Int) {
    Linux("Linux", R.drawable.il_os),
    JavaScript("javascript", R.drawable.il_js),
    PHP("PHP", R.drawable.il_html),
    Networking("Networking", R.drawable.il_networking),
    Cloud("Cloud", R.drawable.il_cloud),
    Docker("Docker", R.drawable.il_kubernetes),
    Kubernetes("Kubernetes", R.drawable.il_kubernetes),
    HTML("HTML", R.drawable.il_html),
    SQL("SQL", R.drawable.il_sql),
    WordPress("wordpress", R.drawable.il_wordpress),
    Code("code", R.drawable.il_code),
    DevOps("devops", R.drawable.il_kubernetes),
    UNKNOWN("unknown", R.drawable.il_unknown);

    companion object {
        fun findSpecific(category: String) =
            values().find {
                category.contains(it.category, ignoreCase = true)
            } ?: UNKNOWN
    }
}
