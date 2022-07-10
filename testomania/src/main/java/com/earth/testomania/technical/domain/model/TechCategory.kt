package com.earth.testomania.technical.domain.model

import com.earth.testomania.R

enum class TechCategory(val category: String, val illustration: Int) {
    Linux("Linux", R.drawable.il_os),
    JavaScript("javascript", R.drawable.il_js),
    PHP("PHP", R.drawable.il_php),
    Networking("Networking", R.drawable.il_networking),
    Cloud("Cloud", R.drawable.il_cloud),
    Docker("Docker", R.drawable.il_docker),
    Kubernetes("Kubernetes", R.drawable.il_kubernetes),
    HTML("HTML", R.drawable.il_html),
    SQL("SQL", R.drawable.il_sql),
    WordPress("wordpress", R.drawable.il_wordpress),
    UNKNOWN("unknown", R.drawable.il_unknown),
    Code("code", R.drawable.il_code);

    companion object {
        fun findSpecific(category: String) =
            values().find {
                category.contains(it.category, ignoreCase = true)
            } ?: UNKNOWN
    }
}