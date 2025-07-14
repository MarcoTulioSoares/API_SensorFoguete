from http.server import BaseHTTPRequestHandler, HTTPServer
import json
import random
import threading
import time
from datetime import datetime

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
        },
        "tensao": {
            "voltage_base": 0.0,
            "voltage_rocket": 0.0
        },
        "gps": {
            "latitude": 0.0,
            "longitude": 0.0,
            "altitude": 0.0,
            "day": 0,
            "month": 0,
            "year": 0,
            "hour": 0,
            "minute": 0,
            "second": 0
        },
        "esp_now_channel": 1,
        "mac_address": "2C:BC:BB:4B:E4:BC"
    },
    "timestamp": 0.0
}

# Atualiza os dados a cada 100ms
def update_sensor_data():
    while True:
        now = datetime.utcnow()

        # Altímetro
        sensor_data["sensors"]["altimetro"]["altitude"] = round(random.uniform(1000, 1020), 2)
        sensor_data["sensors"]["altimetro"]["pressure"] = round(random.uniform(95, 105), 2)

        # Acelerômetro
        sensor_data["sensors"]["acelerometro"]["accX"] = round(random.uniform(-10, 10), 2)
        sensor_data["sensors"]["acelerometro"]["accY"] = round(random.uniform(-10, 10), 2)
        sensor_data["sensors"]["acelerometro"]["accZ"] = round(random.uniform(-10, 10), 2)
        sensor_data["sensors"]["acelerometro"]["gyroX"] = round(random.uniform(-1, 1), 2)
        sensor_data["sensors"]["acelerometro"]["gyroY"] = round(random.uniform(-1, 1), 2)
        sensor_data["sensors"]["acelerometro"]["gyroZ"] = round(random.uniform(-1, 1), 2)
        sensor_data["sensors"]["acelerometro"]["temp"] = round(random.uniform(20, 35), 2)
        sensor_data["sensors"]["acelerometro"]["roll"] = round(random.uniform(0, 180), 2)
        sensor_data["sensors"]["acelerometro"]["pitch"] = round(random.uniform(0, 180), 2)

        # Tensão
        sensor_data["sensors"]["tensao"]["voltage_base"] = round(random.uniform(11, 12.6), 2)
        sensor_data["sensors"]["tensao"]["voltage_rocket"] = round(random.uniform(9, 12.6), 2)

        # GPS
        sensor_data["sensors"]["gps"]["latitude"] = round(random.uniform(-90, 90), 6)
        sensor_data["sensors"]["gps"]["longitude"] = round(random.uniform(-180, 180), 6)
        sensor_data["sensors"]["gps"]["altitude"] = round(random.uniform(100, 500), 2)
        sensor_data["sensors"]["gps"]["day"] = now.day
        sensor_data["sensors"]["gps"]["month"] = now.month
        sensor_data["sensors"]["gps"]["year"] = now.year
        sensor_data["sensors"]["gps"]["hour"] = now.hour
        sensor_data["sensors"]["gps"]["minute"] = now.minute
        sensor_data["sensors"]["gps"]["second"] = now.second

        # Timestamp (simulação)
        sensor_data["timestamp"] = round(time.time(), 2)

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
    threading.Thread(target=update_sensor_data, daemon=True).start()
    server_address = ('', 8080)
    httpd = HTTPServer(server_address, ESPHandler)
    print("ESP Simulator rodando em http://localhost:8080/json")
    httpd.serve_forever()
