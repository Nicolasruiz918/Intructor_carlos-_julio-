async function request(url, method = GET, data = null) {
  let headersList = {
    "Accept": "*/*",
    "User-Agent": "calichesoft"
  };

  let response = await fetch(url, {
    method: method,
    body: data,
    headers: headersList
  });

  return response.json();
}