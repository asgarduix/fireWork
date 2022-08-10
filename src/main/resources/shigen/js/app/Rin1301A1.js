function convertCurrency(num) {
    var parts = num.toString().split('.');
    parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    return parts.join('.');
}

function floatMul(arg1, arg2, d) {
    var m = 0,
        s1 = arg1.toString(),
        s2 = arg2.toString(),
        resultVal
    try { m += s1.split(".")[1].length; } catch (e) {}
    try { m += s2.split(".")[1].length; } catch (e) {}
    resultVal = Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);
    return typeof d !== "number" ? Number(resultVal) : Number(resultVal.toFixed(parseInt(d)));
}

function floatDiv(arg1, arg2, d) {
    var r1 = arg1.toString(),
        r2 = arg2.toString(),
        m, resultVal
    m = (r2.split(".")[1] ? r2.split(".")[1].length : 0) - (r1.split(".")[1] ? r1.split(".")[1].length : 0);
    resultVal = Number(r1.replace(".", "")) / Number(r2.replace(".", "")) * Math.pow(10, m);
    return typeof d !== "number" ? Number(resultVal) : Number(resultVal.toFixed(parseInt(d)));
}

function floatAdd(arg1, arg2, d) {
    var r1, r2, m;
    try { r1 = arg1.toString().split(".")[1].length; } catch (e) { r1 = 0; }
    try { r2 = arg2.toString().split(".")[1].length; } catch (e) { r2 = 0; }
    m = Math.pow(10, Math.max(r1, r2));
    resultVal = (floatMul(arg1, m, d) + floatMul(arg2, m, d)) / m;
    return typeof d !== "number" ? Number(resultVal) : Number(resultVal.toFixed(parseInt(d)));
}

function dateFormat(current_datetime) {
    let formatted_date = current_datetime.getFullYear() + "/" + (current_datetime.getMonth() + 1) + "/" + current_datetime.getDate();
    let formatted_minute = current_datetime.getMinutes() < 10 ? "0" + current_datetime.getMinutes() : current_datetime.getMinutes();
    let formatted_seconds = current_datetime.getSeconds() < 10 ? "0" + current_datetime.getSeconds() : current_datetime.getSeconds();

    return formatted_date + " " + current_datetime.getHours() + ":" + formatted_minute + ":" + formatted_seconds;
}