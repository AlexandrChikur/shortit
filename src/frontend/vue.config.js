module.exports = {
    devServer: {
        port: 8000,
        proxy: {
            '/r': {
                target: 'http://localhost:8000',
                ws: true,
                changeOrigin: true
            }
        }
    }
}