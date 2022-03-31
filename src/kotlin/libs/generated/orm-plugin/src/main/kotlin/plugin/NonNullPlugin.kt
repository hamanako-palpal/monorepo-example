package plugin

import org.mybatis.generator.api.IntrospectedTable
import org.mybatis.generator.api.PluginAdapter
import org.mybatis.generator.api.dom.kotlin.KotlinFile
import org.mybatis.generator.api.dom.kotlin.KotlinType

class NonNullPlugin : PluginAdapter() {
    override fun validate(warnings: MutableList<String>?): Boolean {
        return true
    }

    override fun kotlinDataClassGenerated(kotlinFile: KotlinFile?, dataClass: KotlinType?, introspectedTable: IntrospectedTable?): Boolean {
        return super.kotlinDataClassGenerated(kotlinFile, dataClass, introspectedTable)
    }
}
