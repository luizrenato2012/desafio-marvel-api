package br.com.teste.service.parse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import br.com.teste.model.Series;
import br.com.teste.model.Thumbnail;
import br.com.teste.model.URLTyped;
import br.com.teste.resource.dto.SeriesDTO;
import br.com.teste.resource.dto.ThumbnailDTO;

public class BeanDTOParser {

	public Series parseSeriesDTO(SeriesDTO seriesDto) {
		Series series = new Series();
		series.setModified(LocalDateTime.now());
		BeanUtils.copyProperties(seriesDto, series, "resourceURI","urls",
				"thumbnail");
		List<URLTyped> urls = null;
		if (!CollectionUtils.isEmpty(seriesDto.getUrls())) {
			urls = seriesDto.getUrls()
				.stream()
				.map(urlDto -> new URLTyped(null, urlDto.getType(), urlDto.getUrl()))
				.collect(Collectors.toList());
		}
		series.setUrls(urls);
		ThumbnailDTO thumbnailDTO = seriesDto.getThumbnail();
		if ( thumbnailDTO!=null) {
			series.setThumbnail(new Thumbnail(null, thumbnailDTO.getPath(), thumbnailDTO.getExtension()));
		}
		return series;
	}
}
