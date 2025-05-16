from http.server import BaseHTTPRequestHandler, HTTPServer
import json
import random
import threading
import time

# Dados que serão atualizados a cada 100ms
sensor_data = {
    "sensors": {
        "altimetro": {
            "altitude": 0.0,
            "pressure": 0.0
        },
        "acelerometro": {
            "accX": 0.0,
            "accY": 0.0,
            "accZ": 0.0,
            "gyroX": 0.0,
            "gyroY": 0.0,
            "gyroZ": 0.0,
            "temp": 0.0,
            "roll": 0.0,
            "pitch": 0.0
        }
    },
    "timestamp": 0
}

# Atualiza os dados a cada 100ms
def update_sensor_data():
    while True:
        sensor_data["sensors"]["altimetro"]["altitude"] = round(random.uniform(1000, 1020), 2)
        sensor_data["sensors"]["altimetro"]["pressure"] = round(random.uniform(95, 105), 2)

        sensor_data["sensors"]["acelerometro"]["accX"] = round(random.uniform(-10, 10), 2)
        sensor_data["sensors"]["acelerometro"]["accY"] = round(random.uniform(-10, 10), 2)
        sensor_data["sensors"]["acelerometro"]["accZ"] = round(random.uniform(-10, 10), 2)
        sensor_data["sensors"]["acelerometro"]["gyroX"] = round(random.uniform(-1, 1), 2)
        sensor_data["sensors"]["acelerometro"]["gyroY"] = round(random.uniform(-1, 1), 2)
        sensor_data["sensors"]["acelerometro"]["gyroZ"] = round(random.uniform(-1, 1), 2)
        sensor_data["sensors"]["acelerometro"]["temp"] = round(random.uniform(20, 35), 2)
        sensor_data["sensors"]["acelerometro"]["roll"] = round(random.uniform(0, 180), 2)
        sensor_data["sensors"]["acelerometro"]["pitch"] = round(random.uniform(0, 180), 2)

        sensor_data["timestamp"] += 1
        time.sleep(0.1)  # 100ms

# HTTP handler
class ESPHandler(BaseHTTPRequestHandler):
    def do_GET(self):
        if self.path == '/json':
            self.send_response(200)
            self.send_header('Content-type', 'application/json')
            self.end_headers()
            self.wfile.write(json.dumps(sensor_data).encode())
        else:
            self.send_response(404)
            self.end_headers()

if __name__ == '__main__':
    # Inicia a thread de geração de dados
    threading.Thread(target=update_sensor_data, daemon=True).start()

    # Inicia o servidor
    server_address = ('', 8080)
    httpd = HTTPServer(server_address, ESPHandler)
    print("ESP Simulator rodando em http://localhost:8080/json")
    httpd.serve_forever()
