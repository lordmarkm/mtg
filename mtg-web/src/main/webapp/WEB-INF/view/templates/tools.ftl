<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<#macro nl2br string>
  ${string?replace('\n','<br/>')}
</#macro>

<#macro flag country>
  <#if country.flag??>
  <img class="tinyflag" title="${country.name}" src="/image/${country.flag.id}" />
  </#if>
</#macro>

<#macro citynav city active>
<div class="tabbable">
  <ul class="nav nav-tabs">
    <li <#if active=0>class="active"</#if>><a href="<@spring.url '/ct/${city.urlFragment }' />"><i class="fam-comments"></i> Discussions</a></li>
    <@sec.authorize access="isAuthenticated()">
    <li <#if active=1>class="active"</#if>><a href="<@spring.url '/ct/${city.urlFragment }/newpost' />"><i class="fam-comment-add"></i> Post</a>
    </@sec.authorize>
    <@sec.authorize access="isAnonymous()">
    <li class="disabled" title="Please login to post"><a href="javascript:;" style="outline: 0;"><i class="fam-comment-add"></i> Post</a>
    </@sec.authorize>
    <li <#if active=2>class="active"</#if>><a href="<@spring.url '/ct/${city.urlFragment }/players' />"><i class="fam-book-open"></i> Binders</a></li>
    <#if admin || moderator>
    <li <#if active=3>class="active"</#if>><a href="<@spring.url '/ct/${city.urlFragment}/manage' />"><i class="fam-group-gear"></i> Manage</a>
    </#if>
  </ul>
</div>
</#macro>