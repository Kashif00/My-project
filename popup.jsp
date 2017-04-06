<Doctype jsp>
<jsp>
<head>
<style>
#ac-wrapper {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgb(211,211,211);
    z-index: 1001;
}
#popup {
    width: 400px;
    height: 250px;
    background: #FFFFFF;
    border: 2px solid 	#ADD8E6;
    border-radius: 25px;
    -moz-border-radius: 25px;
    -webkit-border-radius: 25px;
    box-shadow: #64686e 0px 0px 3px 3px;
    -moz-box-shadow: #64686e 0px 0px 3px 3px;
    -webkit-box-shadow: #64686e 0px 0px 3px 3px;
    position: relative;
    top: 150px;
    left: 355px;
}
img{
height:100px;
width:100px;
}
</style>
<script>
function PopUp(hideOrshow) {
    if (hideOrshow == 'hide') document.getElementById('ac-wrapper').style.display = "none";
    else document.getElementById('ac-wrapper').removeAttribute('style');
}
window.onload = function () {
    setTimeout(function () {
        PopUp('show');
    }, 500);
}
</script>
</head>
<body>
<div id="ac-wrapper" style='display:none'>
    <div id="popup">
        <center>
             <h2>Are You sure you want to Login</h2>
                <img src="img\T4Assur.png"><br><br><br>
            <input type="submit" name="submit" value="Yes" onClick="location.href='login.jsp'" />         <input type="submit" name="submit" value="No" onClick="location.href='index.jsp'" /> 

        </center>
    </div>
</div>
</body>
</jsp>