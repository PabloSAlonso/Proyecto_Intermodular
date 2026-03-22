using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System.Web.Script.Serialization; // Requires System.Web.Extensions reference

namespace Klyer
{
    public class ApiHelper
    {
        private readonly HttpClient _httpClient;
        private readonly string _baseUrl;

        public ApiHelper(string baseUrl = "http://localhost:8080/tema5maven/rest")
        {
            _baseUrl = baseUrl;
            _httpClient = new HttpClient();
        }

        public async Task<T> GetAsync<T>(string endpoint)
        {
            try {
                var response = await _httpClient.GetAsync($"{_baseUrl}{endpoint}");
                response.EnsureSuccessStatusCode();
                var json = await response.Content.ReadAsStringAsync();
                var serializer = new JavaScriptSerializer();
                return serializer.Deserialize<T>(json);
            }
            catch (Exception ex) {
                // Log error
                throw new Exception($"API call failed: {ex.Message}");
            }
        }

        public async Task<bool> PostAsync(string endpoint, object data)
        {
            try {
                var serializer = new JavaScriptSerializer();
                var json = serializer.Serialize(data);
                var content = new StringContent(json, Encoding.UTF8, "application/json");

                var response = await _httpClient.PostAsync($"{_baseUrl}{endpoint}", content);
                return response.IsSuccessStatusCode;
            }
            catch (Exception ex) {
                // Log error
                throw new Exception($"API call failed: {ex.Message}");
            }
        }

        public async Task<bool> PutAsync(string endpoint, object data)
        {
            try {
                var serializer = new JavaScriptSerializer();
                var json = serializer.Serialize(data);
                var content = new StringContent(json, Encoding.UTF8, "application/json");

                var response = await _httpClient.PutAsync($"{_baseUrl}{endpoint}", content);
                return response.IsSuccessStatusCode;
            }
            catch (Exception ex) {
                // Log error
                throw new Exception($"API call failed: {ex.Message}");
            }
        }

        public async Task<bool> DeleteAsync(string endpoint)
        {
            try {
                var response = await _httpClient.DeleteAsync($"{_baseUrl}{endpoint}");
                return response.IsSuccessStatusCode;
            }
            catch (Exception ex) {
                // Log error
                throw new Exception($"API call failed: {ex.Message}");
            }
        }
    }
}