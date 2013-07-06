<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<#macro nl2br string>
  ${string?replace('\n','<br/>')}
</#macro>

<#macro flag country>
  <#if country.flag??>
  <img class="tinyflag" title="${country.name}" src="/image/${country.flag.id}" />
  </#if>
</#macro>

<#macro postparentLink post>
  <#switch post.parent.parentType>
    <#case 'frontpage'>
      <a href="<@spring.url '/support/frontpage' />">the frontpage</a>
      <#break>
    <#case 'meetup'>
      <a href="<@spring.url '/m/${post.parent.parent.urlFragment}' />">${post.parent.parent.name}</a>
      <#break>
    <#case 'city'>
      <a href="<@spring.url '/ct/${post.parent.parent.urlFragment}' />">${post.parent.parent.name}</a>
      <#break>
    <#case 'country'>
      <a href="<@spring.url '/cy/${post.parent.parent.urlFragment}' />">${post.parent.parent.name}</a>
      <#break>
  </#switch>
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

<#macro meetupnav meetup active>
<div class="tabbable">
  <ul class="nav nav-tabs">
    <li <#if active=0>class="active"</#if>><a href="<@spring.url '/m/${meetup.urlFragment }' />"><i class="fam-comments"></i> Discussions</a></li>
    <@sec.authorize access="isAuthenticated()">
    <li <#if active=1>class="active"</#if>><a href="<@spring.url '/m/${meetup.urlFragment }/newpost' />"><i class="fam-comment-add"></i> Post</a>
    </@sec.authorize>
    <@sec.authorize access="isAnonymous()">
    <li class="disabled" title="Please login to post"><a href="javascript:;" style="outline: 0;"><i class="fam-comment-add"></i> Post</a>
    </@sec.authorize>
    <li <#if active=2>class="active"</#if>><a href="<@spring.url '/m/${meetup.urlFragment }/players' />"><i class="fam-book-open"></i> Binders</a></li>
    <#if admin || moderator>
    <li <#if active=3>class="active"</#if>><a href="<@spring.url '/m/${meetup.urlFragment}/manage' />"><i class="fam-group-gear"></i> Manage</a>
    </#if>
  </ul>
</div>
</#macro>

<#macro context contextcomment ancestors index username>
  <#if !ancestors[index]??>
    <@showcomment comment=contextcomment username=username highlight=true delete=false />
    <#return>
  <#else>
    <#assign comment = ancestors[index]>
  </#if>
  
  <div class="comment-container">
    <div class="comment-header">
      <#if comment.deleted>
      <span class="muted">[deleted]</span>
      <#else>
      <a href="javascript:;" class="comment-expand">[-]</a> <a href="<@spring.url '/u/${comment.author.name}' />">${comment.author.name}</a> 
      </#if>
      <span class="fromNow muted tiny">${comment.postdate}</span>
    </div>
    <div class="comment-body">
      <#if comment.deleted>
      <span class="muted">[deleted]</span>
      <#else>
      <div>
      <@nl2br string=comment.text />
      </div>
      <small class="comment-controls" data-comment-id="${comment.id}">
        <@sec.authorize access="isAuthenticated()">
        <a class="link-comment-reply" href="javascript:;">reply</a>
        </@sec.authorize>
        <#if username == comment.author.name>
        <a class="link-comment-delete" href="javascript:;">delete</a>
        </#if>
        <a href="<@spring.url '/comment/permalink/${comment.id }' />">permalink</a>
      </small>
      <div class="reply">
      </div>
      </#if>
      <div class="replies">
        <#if (index == (ancestors?size-1))>
          <@showcomment comment=contextcomment username=username highlight=true delete=false />
        <#else>
          <@context contextcomment ancestors index+1 username />
        </#if>
      </div>
    </div>
  </div>
</#macro>

<#macro showcomment comment username highlight delete>
  <div class="comment-container">
    <div class="comment-header">
      <#if comment.deleted>
      <span class="muted">[deleted]</span>
      <#else>
      <a href="javascript:;" class="comment-expand">[-]</a> <a href="<@spring.url '/u/${comment.author.name}' />">${comment.author.name}</a> 
      </#if>
      <span class="fromNow muted tiny">${comment.postdate}</span>
    </div>
    <div class="comment-body">
      <#if comment.deleted>
      <span class="muted">[deleted]</span>
      <#else>
      <div>
      <div class="comment-text<#if highlight> alert alert-info mb0</#if>"><@nl2br string=comment.text /></div>
      </div>
      <small class="comment-controls" data-comment-id="${comment.id}">
        <@sec.authorize access="isAuthenticated()">
        <a class="link-comment-reply" href="javascript:;">reply</a>
        </@sec.authorize>
        <#if username == comment.author.name || delete>
        <a class="link-comment-delete" href="javascript:;">delete</a>
        </#if>
        <a href="<@spring.url '/comment/permalink/${comment.id }' />">permalink</a>
      </small>
      <div class="reply">
      </div>
      </#if>
      <div class="replies">
      <#list comment.replies as reply>
        <@showcomment reply username false false />
      </#list>
      </div>
    </div>
  </div>
</#macro>