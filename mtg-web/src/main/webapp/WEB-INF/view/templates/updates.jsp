<#import "/spring.ftl" as spring />

<div class="well">
  <h2>
    Updates<br/>
    <small>The latest from Magic Online Binder</small>
  </h2>
  
  <dl id="updates-list">
    <dt>June 18, 2013</dt>
    <dd>Added view individual card page</dd>
  
    <dt>June 17, 2013</dt>
    <dd>Added the <a href="<@spring.url '/support/upcoming' />">Upcoming Features</a> page</dd>
    
    <dt>June 16, 2013</dt>
    <dd>
      <ol>
        <li>Enable uploading of images (also moved storage from DB to filesystem)
        <li>Display images
        <li>Enable uploading profile pic
      </ol>
    </dd>
  
    <dt>June 15, 2013</dt>
    <dd>Nothing works, but I added this page yey!</dd>
  </dl>
  
  <p><a href="<@spring.url '/support/updates' />">View all</a>
  <p><a href="<@spring.url '/support/upcoming' />">Also see the Upcoming features</a>
</div>

<style>
#updates-list dd {
  margin-bottom: 5px;
}
</style>