<#import "/spring.ftl" as spring />

<div class="well">
  <h2>
    Updates<br /> <small>The latest from Magic Online Binder</small>
  </h2>

  <dl id="updates-list">
    <dt>June 26, 2013
    <dd>Tomescour.org now online, yey! Also renamed Tomescour from Magic Online Binder
    <dt>June 22, 2013
    <dd>
      <ol>
        <li>Added the Want list
        <li>Added email verification
      </ol>
    </dd>
    <dt>June 21, 2013
    <dd>Users can now browse binders and filter by location
    <dt>June 20, 2013
    <dd>
      <ol>
        <li>Users can now edit Contact Info
        <li>On card search, cards with the same name are now displayed as one result, and then displayed as a table when clicked
        <li>It is now possible to find cards in binders by going to the card's page and then clicking Find in Binders
      </ol>
    <dt>June 18, 2013
    <dd>Added card search (from the navbar)
    
    <dt>June 17, 2013</dt>
    <dd>
      <ol>
        <li>Added the <a href="<@spring.url '/support/upcoming' />">Upcoming
            Features</a> page
        <li>Added individual card page
      </ol>
    </dd>

    <dt>June 16, 2013</dt>
    <dd>
      <ol>
        <li>Enable uploading of images (also moved storage from DB
          to filesystem)
        <li>Display images
        <li>Enable uploading profile pic
      </ol>
    </dd>

    <dt>June 15, 2013</dt>
    <dd>Nothing works, but I added this page yey!</dd>
  </dl>

  <p>
    <a href="<@spring.url '/support/updates' />">View all</a>
  <p>
    <a href="<@spring.url '/support/upcoming' />">Also see the
      Upcoming features</a>
</div>

<style>
#updates-list dd {
  margin-bottom: 5px;
}
</style>