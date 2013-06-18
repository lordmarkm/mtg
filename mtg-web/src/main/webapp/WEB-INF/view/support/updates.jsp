<#import "/spring.ftl" as spring />

<h1>
  Updates<br/>
  <small>The latest from Magic Online Binder</small>
</h1>

<dl id="updates-list">
  <dt>June 18, 2013
  <dd>Added card search (from the navbar)

  <dt>June 17, 2013</dt>
  <dd>
    <ol>
      <li>Added the <a href="<@spring.url '/support/upcoming' />">Upcoming Features</a> page
      <li>Added individual card page
    </ol>
  </dd>
  
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

<p><a href="<@spring.url '/support/upcoming' />">Also see the Upcoming features</a>

<style>
#updates-list dd {
  margin-bottom: 5px;
}
</style>