<#import "/spring.ftl" as spring />
<#import "../templates/tools.ftl" as tools />

<style>
ul.nav > li > a > img {
  vertical-align: top;
}
ul.nav > li {
  height: 35px;
}
</style>

<#if player?? && (player.country?? || (player.cities?size > 0) || (player.meetups?size > 0))>
<h4>Your communities</h4>
  <ul class="nav nav-pills">
  <#if player.country??>
  <li><a href="<@spring.url '/cy/${player.country.urlFragment }' />"><@tools.flag country=player.country />${player.country.name }</a></li>
  </#if>

  <#list player.cities as city>
  <li><a href="<@spring.url '/ct/${city.urlFragment }' />">${city.name }</a></li>  
  </#list>

  <#list player.meetups as meetup>
  <li><a href="<@spring.url '/m/${meetup.urlFragment }' />">${meetup.name }</a></li>
  </#list>
  </ul>
</#if>

<#if relatedCities?? && (relatedCities?size > 0 || relatedMeetups?size > 0)>
<h4>Possibly related</h4>
  <ul class="nav nav-pills">
  <#list relatedCities as city>
  <li><a href="<@spring.url '/ct/${city.urlFragment }' />">${city.name }</a></li>
  </#list>
  
  <#list relatedMeetups as meetups>
  <li><a href="<@spring.url '/m/${meetup.urlFragment }' />">${meetup.name }</a></li>
  </#list>
  </ul>
</#if>

<#if (cities?size > 0 || meetups?size > 0)>
  <#if player??>
  <h4>Others</h4>
  <#else>
  <h4>Communities</h4>
  </#if>

  <ul class="nav nav-pills">
    <#list countries as country>
    <li><a href="<@spring.url '/cy/${country.urlFragment }' />"><@tools.flag country=country />${country.name }</a></li>
    </#list>

    <#list cities as city>
    <li><a href="<@spring.url '/ct/${city.urlFragment }' />">${city.name }</a></li>
    </#list>
  
    <#list meetups as meetup>
    <li><a href="<@spring.url '/m/${meetup.urlFragment }' />">${meetup.name }</a></li>
    </#list>
  </ul>
</#if>