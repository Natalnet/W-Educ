<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="admin">
		<title>Grails Forum</title>
	</head>
	<body>
        <div class="pagination">
            <g:paginate total="${numberOfComments}" params="${[threadId:thread.id]}"/>
        </div>

        <div class="section">
            <div class="sectionTitle">
                ${thread.subject}
            </div>
            <g:each in="${comments}" var="comment">
                <div class="comment">
                    <div>
                        <b>${comment.commentBy.username}</b>
                        <span class="topicDesc">
                            <g:formatDate date="${comment.createDate}" format="dd MMM yyyy hh:mma"/>
                        </span>
                    </div>

                    ${comment.body}
                </div>
            </g:each>
            <sec:ifLoggedIn>
                <div class="comment">
                    <h2>Reply</h2>
                    <g:form>
                        <g:textArea name="body"></g:textArea>
                        <g:hiddenField name="threadId" value="${thread.id}"/>
                        <fieldset class="buttons">
                            <g:actionSubmit value="Post Comment" action="postReply"/>
                        </fieldset>

                    </g:form>
                </div>
            </sec:ifLoggedIn>
        </div>

        <div class="pagination">
            <g:paginate total="${numberOfComments}" params="${[threadId:thread.id]}"/>
        </div>
	</body>
</html>