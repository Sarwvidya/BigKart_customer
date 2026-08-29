<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>BigKart Customer</title>
    <style>
        body { font-family: sans-serif; display: flex; flex-direction: column; align-items: center; justify-content: center; height: 100vh; background-color: #f0f2f5; margin: 0; }
        .card { background: white; padding: 2rem; border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); text-align: center; }
        .number { font-size: 4rem; font-weight: bold; color: #34a853; margin: 1rem 0; transition: transform 0.2s; }
        .updated { transform: scale(1.1); color: #2e8b46; }
    </style>
    <script>
        let currentDisplayedNumber = null;
        
        function fetchLatestNumber() {
            fetch('/latest-number')
                .then(response => response.text())
                .then(number => {
                    const numberDiv = document.getElementById('latestNumber');
                    if (currentDisplayedNumber !== number) {
                        numberDiv.innerText = number;
                        currentDisplayedNumber = number;
                        
                        // Add a small animation effect
                        numberDiv.classList.add('updated');
                        setTimeout(() => {
                            numberDiv.classList.remove('updated');
                        }, 300);
                    }
                })
                .catch(err => console.error("Error fetching latest number:", err));
        }

        // Poll every 1 second
        setInterval(fetchLatestNumber, 1000);
        
        // Initial fetch
        window.onload = fetchLatestNumber;
    </script>
</head>
<body>
    <div class="card">
        <h1>BigKart Customer View</h1>
        <p>Latest Broadcasted Number:</p>
        <div id="latestNumber" class="number">0</div>
        <p style="color: #666; font-size: 0.9rem;">Auto-updating via Kafka...</p>
    </div>
</body>
</html>
