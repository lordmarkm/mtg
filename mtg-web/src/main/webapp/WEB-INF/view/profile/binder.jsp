<#import "/spring.ftl" as spring />
<#import "../templates/tools.ftl" as tools />

<#if !binder??>
<div class="alert alert-error">
  Binder not found
</div>
<#else>

<style>
.thumbnails {
  margin-left: 20px !important;
  padding-top: 40px;
}
.thumbnail {
  height: 260px;
}
.card-name {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  text-align: center;
}
.carousel-indicators li {
  background-color: #999999;
  cursor: pointer;
}
.carousel-indicators .active {
  background-color: #0088CC;
}
</style>

<h3>${binder.name }</h3>
<p><@tools.nl2br string=binder.description />

<div id="binder-carousel" class="carousel slide">
  <ol class="carousel-indicators">
    <#list binder.pages as page>
    <li data-target="#binder-carousel" data-slide-to="${page_index }" <#if page_index == 0>class="active"</#if>></li>
    </#list>
  </ol>
  <!-- The actual pages -->
  <div class="carousel-inner">
    <#list binder.pages as page>
    <div class="item <#if page_index == 0>active</#if>">
      <ul class="thumbnails">
      <#list 0..8 as slot>
        <#if page.bundles[slot]??>
        <#assign bundle=page.bundles[slot]>
        <li class="span2" title="${bundle.card.name }">
          <div class="thumbnail">
            <img src="<@spring.url '/image/${bundle.card.image.id }' />" />
            <div class="card-name">${bundle.card.name }</div>
            <div class="center"><strong>(${bundle.count })</strong></div>
          </div>
        </li>
        <#else>
        <li class="span2" title="Nothing here">
          <div class="thumbnail">
            <img src="<@spring.url '/images/no-img.jpg' />" />
            <div class="card-name">Empty</div>
          </div>
        </li>
        </#if>
      </#list>
      </ul>
    </div>
    </#list>
  </div>
  <!-- Carousel nav -->
  <a class="carousel-control left" href="#binder-carousel" data-slide="prev">&lsaquo;</a>
  <a class="carousel-control right" href="#binder-carousel" data-slide="next">&rsaquo;</a>
</div>
  
</#if>

