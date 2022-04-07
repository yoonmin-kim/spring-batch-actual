package hello.batch.actual.batch.chunk.writer;import java.util.List;import org.springframework.batch.item.ExecutionContext;import org.springframework.batch.item.file.FlatFileItemWriter;import org.springframework.batch.item.file.transform.DelimitedLineAggregator;import org.springframework.core.io.FileSystemResource;import hello.batch.actual.batch.domain.ApiRequestVO;import hello.batch.actual.batch.domain.ApiResponseVO;import hello.batch.actual.service.AbstractApiService;public class ApiItemWriter3 extends FlatFileItemWriter<ApiRequestVO> {	private final AbstractApiService apiService;	public ApiItemWriter3(AbstractApiService apiService) {		this.apiService = apiService;	}	@Override	public void write(List<? extends ApiRequestVO> items) throws Exception {		ApiResponseVO responseVO = apiService.service(items);		System.out.println("responseVO = " + responseVO);		items.stream().forEach(item -> item.setResponseVO(responseVO));		super.setResource(new FileSystemResource("C:\\spring-batch-actual\\src\\main\\resources\\product3.txt"));		super.open(new ExecutionContext());		super.setLineAggregator(new DelimitedLineAggregator<>());		super.setAppendAllowed(true);		super.write(items);	}}