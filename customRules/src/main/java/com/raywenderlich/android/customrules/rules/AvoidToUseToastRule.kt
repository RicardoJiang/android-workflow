package com.raywenderlich.android.customrules.rules

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.descriptors.containingPackage
import org.jetbrains.kotlin.psi.KtReferenceExpression
import org.jetbrains.kotlin.resolve.BindingContext

class AvoidToUseToastRule() : Rule() {
    override val issue = Issue(
        "AvoidUseToastRule",
        Severity.Maintainability,
        "Don’t use android.widget.Toast.show",
        Debt.TWENTY_MINS
    )

    override fun visitReferenceExpression(expression: KtReferenceExpression) {
        super.visitReferenceExpression(expression)
        if (expression.text == "makeText") {
            val referenceDescriptor = bindingContext.get(BindingContext.REFERENCE_TARGET, expression)
            val packageName = referenceDescriptor?.containingPackage()?.asString()
            val className = referenceDescriptor?.containingDeclaration?.name?.asString()
            if (packageName == "android.widget" && className == "Toast") {
                report(
                    CodeSmell(
                        issue,
                        Entity.from(expression),
                        "禁止直接使用Toast，建议使用xxxUtils"
                    )
                )
            }
        }
    }
}
