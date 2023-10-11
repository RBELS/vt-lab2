
const sendDelete = async (context, bookId) => {
    await fetch(`${context}/book?id=${bookId}`, { method: 'DELETE' })
    window.location.assign(`${context}/`)
}
