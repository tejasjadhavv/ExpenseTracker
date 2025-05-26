# 💰 Java Console Expense Tracker

A simple, CLI-based Expense Tracker built with Java — allows users to:

- Add income and expenses
- Categorize transactions (e.g., **Food**, **Rent**, **Travel**)
- Save/load data per user
- View monthly summaries
- Delete transactions
- 💾 Store data as `.txt` files under each user's name

## 📂 Project Structure
'''
src/
├── org/
│ └── demo/
│ ├── Main.java // CLI entry point
│ ├── Transaction.java // Model
│ ├── TransactionManager.java // Business logic
│ ├── FileManager.java // File I/O logic
│ └── util/Constants.java // Category/type constants
├── users/ // Directory containing user-specific .txt files
└── README.md
'''
## 🧪 Features

- ✅ Add income/expense transactions
- ✅ Categorize transactions
- ✅ Per-user transaction files (e.g., `users/john.txt`)
- ✅ View monthly summaries
- ✅ Show categorized expense breakdown (Food, Rent, Travel)
- ✅ Delete transactions by index
- ✅ Local file storage for persistence

---

## 💻 How to Run

### 1. Compile:

javac -d out src/org/demo/*.java
java -cp out org.demo.Main

![image](https://github.com/user-attachments/assets/1d873f24-ae9e-4d3b-a1f8-f24b02443f21)
![image](https://github.com/user-attachments/assets/8714f000-b417-4321-afcc-a070270c27d0)
![image](https://github.com/user-attachments/assets/8fadb972-493e-4bc7-956b-f51fa51d5733)
![image](https://github.com/user-attachments/assets/44dd2a70-69dd-4902-b37e-5bc7702c2861)
![image](https://github.com/user-attachments/assets/1e58c36c-2186-4049-b039-1d265404432e)
![image](https://github.com/user-attachments/assets/a70d0046-e140-4b8b-941d-281bd63b621f)
![image](https://github.com/user-attachments/assets/22bb0715-a86c-4242-b47d-77482ae0f62f)






