##Insprired by https://www.geeksforgeeks.org/scrape-google-ngram-viewer-using-python/
"""
Sadly this did not work due to request issues. The amount of requests is limited and therefore not enough
for a simple approach as this one :(
"""
import urllib.parse
import requests
import urllib



class NGramScraper:
    """Scrapes Google Ngram Viewer to get amount of occuring ngrams in
    a certain period of time, corpus 26 is english
    """
    def __init__(self, start_year: int = 2000, end_year: int = 2015, corpus: int = 26, smoothing: int = 0):
        self.base_url = 'https://books.google.com/ngrams/json?content='
        self.start_year = str(start_year)
        self.end_year = str(end_year)
        self.corpus = str(corpus)
        self.smooting = str(smoothing)

    def retrieve_data(self, query: str):
        #convert to url format
        parsed_query = urllib.parse.quote(query)
        #compose the wanted url with base url and query
        url = self.base_url + parsed_query + '&year_start=' + self.start_year + '&year_end=' + self.end_year + '&corpus=' + self.corpus + '&smoothing' + self.smooting

        #request a respionse from url
        response = requests.get(url)

        #get json file from response
        result_json = response.json()

        return_data = []

        if len(result_json) == 0:
            print("No data available for ngram:" + str(query))
            return None
        
        else:
            for num in range(len(result_json)): 
                
                # getting the name 
                return_data.append(  # getting ngram data 
                                    result_json[num]['timeseries']
                                ) 
    
        return return_data 




