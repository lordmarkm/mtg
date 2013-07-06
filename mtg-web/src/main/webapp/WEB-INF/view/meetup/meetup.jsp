<#import "/spring.ftl" as spring />

<span id="active-navbar-class" class="hide">communities</span>

<#if meetup??>

<h3>${meetup.name }</h3>

<ol>
<#list meetup.players as player>
  <li><a href="<@spring.url '/u/${player.name }' />">${player.name }</a></li>
</#list>
</ol>

<#if meetup.moderators?has_content>
<p>Moderators:
  <#list meetup.moderators as moderator>
  <a href="<@spring.url '/u/${moderator.name }' />">${moderator.name }</a>
  </#list>
</#if>

<script>
var postableUrls = {
  load : '<@spring.url "/post/city/${meetup.id?c}/${meetup.urlFragment}?ajax" />'
}
var postUrls = {
        deletePost: '<@spring.url "/post/delete/" />',
        savePost : '<@spring.url "/account/post/save/" />',
        unsavePost : '<@spring.url "/account/post/unsave/" />'
}
</script>
<script src="<@spring.url '/javascript/postable.js' />"></script>
<script src="<@spring.url '/javascript/commentable.js' />"></script>

<#else>
<div class="alert alert-info">Meetup not found</div>
</#if>