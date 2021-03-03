<#import "parts/common.ftlh" as c>


<@c.page>
<div>
<p>
<span><a href="/v1/public/comics">Comics</a></span>
</p>
</div>
    <div>
        <form method="post" action="/v1/public/comics" enctype="multipart/form-data">
            <input type="text" name="name" placeholder="Введите название" />
            <input type="text" name="tag" placeholder="Тэг">
            <input type="text" name="description" placeholder="Описание">
            <input type="file" name="file">
            <button type="submit">Добавить</button>
        </form>
    </div>

</@c.page>
