<#import "/spring.ftl" as spring />
<#import "../templates/tools.ftl" as tools />
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />
<script src="<@spring.url '/javascript/fromnow.js' />"></script>
<@tools.showcomment comment=comment username=username />
