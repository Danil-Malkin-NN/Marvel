<#import "parts/common.ftlh" as c>
<#import "parts/pager.ftlh" as p>


<@p.pager url filter page/>

 <span><a href="/v1/public/characters">Characters</a></span>
 <span><a href="/v1/public/comics/add">Add comics</a></span>
<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/v1/public/comics" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by description">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>

    <div class="card-columns">
       <#list page.content as comics>
           <div class="card">
              <img src="/img/${comics.image}" height="300", width="200">
                   <div class="container">
                   <h4><b><a href="/v1/public/comics/${comics.name}">${comics.name}</a></b></h4>
                   <h3><b>${comics.tag}</b></h3>
                   <p class="card-text">${comics.description}</p>
                   <span><a href="/v1/public/comics/${comics.name}/characters">This comic has these characters ...</a></span>
               </div>
           </div>
       <#else>
           No message
       </#list>
    </div>

</@c.page>