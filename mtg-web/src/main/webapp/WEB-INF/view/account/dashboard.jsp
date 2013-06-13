<#import "/spring.ftl" as spring />
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<h3>Account Details</h3>
<p>Username: <@sec.authentication property="principal.username" />
<p>Authorities: <@sec.authentication property="principal.authorities" />

<h3>Magic Profile</h3>
<p>Name: ${account.player.name}

<h3>Binders</h3>
<ol>
<#list account.player.binders as binder>
  <li>${binder.name }</li>
</#list>
</ol>